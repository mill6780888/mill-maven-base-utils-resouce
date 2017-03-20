package mill.java.base;

/**
 *
 * @title OSUtil.java
 * @package com.bjhit.erange.util
 * @copyright(c) 2009-2012 BJHIT.com All rights reserved.
 * @author xie_pkuan
 * @date 2013-9-16 上午11:05:19
 * @version V2.0
 */

import java.io.*;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @date 2013-9-16 上午11:05:19
 */
public class OSMillUtil {

    /**
     *
     */
    private static Logger logger = LoggerFactory.getLogger(OSMillUtil.class);

    /**
     * 判断是否是Windows操作系统
     *
     * @return
     * @author xie_pkuan
     * @version V2.0
     */
    public static boolean isWindowsOS() {
        boolean isWindowsOS = false;
        String osName = System.getProperty("os.name");
        if (osName.toLowerCase().indexOf("windows") > -1) {
            isWindowsOS = true;
        }
        return isWindowsOS;
    }

    /**
     * 获取本机IP地址
     *
     * @return
     * @author xie_pkuan
     * @version V2.0
     */
    public static String getLocalIP() {
        String sIP = "";
        InetAddress ip = null;
        try {
            // 如果是Windows操作系统
            if (isWindowsOS()) {
                ip = InetAddress.getLocalHost();
            }
            // 如果是Linux操作系统
            else {
                boolean bFindIP = false;
                Enumeration<NetworkInterface> netInterfaces = (Enumeration<NetworkInterface>) NetworkInterface
                        .getNetworkInterfaces();
                while (netInterfaces.hasMoreElements()) {
                    if (bFindIP) {
                        break;
                    }
                    NetworkInterface ni = (NetworkInterface) netInterfaces
                            .nextElement();
                    // ----------特定情况，可以考虑用ni.getName判断
                    // 遍历所有ip
                    Enumeration<InetAddress> ips = ni.getInetAddresses();
                    while (ips.hasMoreElements()) {
                        ip = (InetAddress) ips.nextElement();
                        if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress() // 127.开头的都是lookback地址
                                && ip.getHostAddress().indexOf(":") == -1) {
                            bFindIP = true;
                            break;
                        }
                    }

                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        if (null != ip) {
            sIP = ip.getHostAddress();
        }
        return sIP;
    }

    /**
     * 获取MAC地址，注：Linux系统的网卡1名称必须是eth0
     *
     * @return
     * @throws UnknownHostException
     * @author xie_pkuan
     * @version V2.0
     */
    public static String getMacAddr() {
        String MacAddr = "";
        String str = "";
        try {
            NetworkInterface NIC = null;
            if (isWindowsOS()) {
                InetAddress ip = InetAddress.getLocalHost();
                NIC = NetworkInterface.getByInetAddress(ip);
            } else {
                NIC = NetworkInterface.getByName("eth0");
            }
            if (NIC != null) {
                byte[] buf = NIC.getHardwareAddress();
                for (int i = 0; i < buf.length; i++) {
                    str = str + byteHEX(buf[i]);
                }
                MacAddr = str.toUpperCase();
                // 去除所有的横杠
                MacAddr = MacAddr.replaceAll("-", "");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return MacAddr;

    }

    /**
     * 字节转化成十六进制字符串
     *
     * @param ib
     * @return
     * @author xie_pkuan
     * @version V2.0
     */
    private static String byteHEX(byte ib) {
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a',
                'b', 'c', 'd', 'e', 'f' };
        char[] ob = new char[2];
        ob[0] = Digit[(ib >>> 4) & 0X0F];
        ob[1] = Digit[ib & 0X0F];
        String s = new String(ob);
        return s;
    }

    /**
     * 获取CPU的ID
     *
     * @return
     * @author xie_pkuan
     * @version V2.0
     */
    public static String getProcessId() {
        String processId = "";
        Process process;
        try {
            if (isWindowsOS()) {
                processId = getCPUSerial();
            } else {
                process = Runtime
                        .getRuntime()
                        .exec(new String[] { "/bin/sh", "-c",
                                "dmidecode -t 4 | grep ID|sort -u -r|gawk -F: '{print $2}'" });
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(process.getInputStream()));
                String lineTxt = null;
                if ((lineTxt = reader.readLine()) != null) {
                    // 去除所有的空格
                    processId = lineTxt.replaceAll(" ", "");
                }
                reader.close();
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return processId;
    }

    /**
     * 获取CPU序列号
     * @return
     */
    public static String getCPUSerial() {
        String result = "";
        try {
            File file = File.createTempFile("tmp", ".vbs");
            file.deleteOnExit();
            FileWriter fw = new java.io.FileWriter(file);
            String vbs = "Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\")\n"
                    + "Set colItems = objWMIService.ExecQuery _ \n"
                    + "   (\"Select * from Win32_Processor\") \n"
                    + "For Each objItem in colItems \n"
                    + "    Wscript.Echo objItem.ProcessorId \n"
                    + "    exit for  ' do the first cpu only! \n" + "Next \n";
            // + "    exit for  \r\n" + "Next";
            fw.write(vbs);
            fw.close();
            Process p = Runtime.getRuntime().exec(
                    "cscript //NoLogo " + file.getPath());
            BufferedReader input = new BufferedReader(new InputStreamReader(
                    p.getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                result += line;
            }
            input.close();
            file.delete();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        if (result.trim().length() < 1 || result == null) {
            result = "无CPU_ID被读取";
        }
        return result.trim();
    }
}
