package com.lester.support.util;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class NetUtil {
    public static String getIp() throws Exception {
        String _sIP = "";
        Enumeration<NetworkInterface> iterNetwork;
        Enumeration<InetAddress> iterAddress;
        NetworkInterface network;
        InetAddress address;

        iterAddress = NetworkInterface.getByName("eth0").getInetAddresses();
        if (iterAddress.hasMoreElements()) {
            while (iterAddress.hasMoreElements()) {
                address = iterAddress.nextElement();
                if (address.isAnyLocalAddress())
                    continue;

                if (address.isLoopbackAddress())
                    continue;

                if (address.isMulticastAddress())
                    continue;

                if (address instanceof Inet4Address)
                    return address.getHostAddress();
            }
        } else {
            iterNetwork = NetworkInterface.getNetworkInterfaces();
            while (iterNetwork.hasMoreElements()) {
                network = iterNetwork.nextElement();
                if (!network.isUp())
                    continue;

                if (network.isLoopback()) // If I want loopback, I would use "localhost"  or "127.0.0.1".
                    continue;

                iterAddress = network.getInetAddresses();
                while (iterAddress.hasMoreElements()) {
                    address = iterAddress.nextElement();
                    if (address.isAnyLocalAddress())
                        continue;

                    if (address.isLoopbackAddress())
                        continue;

                    if (address.isMulticastAddress())
                        continue;

                    if (address instanceof Inet4Address)
                        return address.getHostAddress();
                }
            }
        }
        return _sIP;
    }
}
