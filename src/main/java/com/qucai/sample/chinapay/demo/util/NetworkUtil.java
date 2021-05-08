package com.qucai.sample.chinapay.demo.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import org.apache.log4j.Logger;

import com.qucai.sample.chinapay.util.StringUtil;

/**
 * 
 * @author hrtc .
 */
public class NetworkUtil {
    /**
     * LOG .
     */
    public final static Logger LOG = Logger.getLogger(NetworkUtil.class);

    /**
     * 本机ip.
     */
    private static String localIp;

    /**
     * 获得本机ip .
     * 
     * @return 本机ip
     */
    public static String getLocalIp() {
        if (StringUtil.isEmpty(localIp)) {
            localIp = NetworkUtil.getLocalIpAddress();
            LOG.info(String.format("localIp=%s", localIp));
        }

        return localIp;
    }

    /**
     * 
     * @return String
     */
    public static String getLocalIpAddress() {
        Enumeration<NetworkInterface> eni;
        try {
            eni = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }

        while (eni.hasMoreElements()) {
            NetworkInterface ni = eni.nextElement();
            try {
                if ((ni.getMTU() != -1) && (!ni.isLoopback()) && ni.isUp()
                        && (!ni.isVirtual())
                        && ni.getInetAddresses().hasMoreElements()) {
                    Enumeration<InetAddress> eia = ni.getInetAddresses();
                    while (eia.hasMoreElements()) {
                        InetAddress ia = eia.nextElement();

                        if ((!ia.isAnyLocalAddress())
                                && (!ia.isLinkLocalAddress())
                                && (!ia.isLoopbackAddress())) {
                            String hostaddr = ia.getHostAddress();
                            if (hostaddr.contains(":")) {
                                LOG.debug("找到网络地址：IPv6（将被过滤）: " + hostaddr);
                            }

                            if (hostaddr.contains(".")) {
                                LOG.debug("找到网络地址：IPv4（可能为合法）: " + hostaddr);
                                String[] ss = hostaddr.split("[.]");
                                int num = 254;
                                if (ss != null && ss.length > 3
                                        && ss[0] != null
                                        && ss[0].matches("[0-9]+")) {
                                    num = Integer.valueOf(ss[0]);
                                }
                                if (num > 0 && num < 192) {
                                    return hostaddr;
                                }
                            }
                        }
                    }
                }
            } catch (Exception ex) {
                throw new RuntimeException(ex.getMessage(), ex);
            }
        }
        throw new RuntimeException(
                "遍历完成所有网络接口后，无法找到匹配默认逻辑的网络地址。可能网络地址不可用，或事先达成规则未被遵守。规则：过滤192-255的A段地址.");
    }
    
    /**
     * 测试 .
     * @param args 参数
     */
    public static void main(String[] args) {
        System.out.println(getLocalIp());
    }
}
