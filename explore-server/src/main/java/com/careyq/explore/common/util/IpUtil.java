package com.careyq.explore.common.util;

import com.careyq.explore.config.MyRequestContextHolder;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.lionsoul.ip2region.xdb.Searcher;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

/**
 * <p>
 * IP 工具类
 * </p>
 *
 * @author CareyQ
 * @since 2022-10-30
 */
@Slf4j
@UtilityClass
public class IpUtil {

    public String getIpRegion(String ip) {
        String dbPath = Objects.requireNonNull(IpUtil.class.getResource("/ip2region/ip2region.xdb")).getPath();

        byte[] vIndex;
        try {
            vIndex = Searcher.loadVectorIndexFromFile(dbPath);
        } catch (IOException e) {
            log.error("IP 数据文件不存在");
            throw new RuntimeException(e);
        }
        Searcher searcher;
        try {
            searcher = Searcher.newWithVectorIndex(dbPath, vIndex);
        } catch (IOException e) {
            log.error("IP 数据文件创建 vectorIndex 出错");
            throw new RuntimeException(e);
        }

        try {
            String region = searcher.search(ip);
            searcher.close();
            if (StrUtil.isBlank(region)) {
                return "unknown";
            }
            String[] split = region.split("\\|");
            return split[2] + " · " + split[3];
        } catch (Exception e) {
            log.error("IP 查询出错");
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取请求的 IP 地址
     *
     * @param request HttpServletRequest
     * @return IP
     */
    public String getIpAddr(HttpServletRequest request) {
        String clientIp = request.getHeader("x-forwarded-for");

        if (StrUtil.isBlankOrUnknown(clientIp)) {
            clientIp = request.getHeader("Proxy-Client-IP");
        }
        if (StrUtil.isBlankOrUnknown(clientIp)) {
            clientIp = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StrUtil.isBlankOrUnknown(clientIp)) {
            clientIp = request.getRemoteAddr();
        }

        String ip = null;
        if (!StrUtil.isBlankOrUnknown(clientIp) && clientIp.indexOf(StrUtil.COMMA) > 0) {
            String[] ips = clientIp.split(StrUtil.COMMA);
            for (String anIp : ips) {
                if (!isInnerIP(anIp.trim())) {
                    ip = anIp.trim();
                    break;
                }
            }
            if (StrUtil.isBlank(ip)) {
                ip = ips[0].trim();
            }
            clientIp = ip;
        }

        if (StrUtil.isBlankOrUnknown(clientIp)) {
            return StrUtil.LOCALHOST;
        }
        return clientIp;
    }

    /**
     * 获取请求的归属地
     *
     * @return 归属地
     */
    public String getRequestRegion() {
        return getIpRegion(getIpAddr(MyRequestContextHolder.getRequest()));
    }

    /**
     * 是否为内网地址
     * 私有IP：
     * A类  10.0.0.0 - 10.255.255.255
     * B类  172.16.0.0 - 172.31.255.255
     * C类  192.168.0.0 - 192.168.255.255
     *
     * @param ip IP 地址
     * @return 结果
     */
    public boolean isInnerIP(String ip) {
        if (StrUtil.LOCALHOST.equals(ip)) {
            return true;
        }

        long ipNum = getIpNum(ip);
        return isInner(ipNum, getIpNum("10.0.0.0"), getIpNum("10.255.255.255"))
                || isInner(ipNum, getIpNum("172.16.0.0"), getIpNum("172.31.255.255"))
                || isInner(ipNum, getIpNum("192.168.0.0"), getIpNum("192.168.255.255"));
    }

    /**
     * 获取 IP 数值
     *
     * @param ip IP 地址
     * @return 结果
     */
    private long getIpNum(String ip) {
        String[] split = ip.split("\\.");
        int a = Integer.parseInt(split[0]);
        int b = Integer.parseInt(split[1]);
        int c = Integer.parseInt(split[2]);
        int d = Integer.parseInt(split[3]);

        return a * 256L * 256L * 256L + b * 256L * 256L + c * 256L + d;
    }

    private boolean isInner(long ip, long begin, long end) {
        return ip >= begin && ip <= end;
    }
}
