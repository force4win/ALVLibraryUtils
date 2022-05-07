package com.alv.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UtilRed {

	public static String obtenerMacAddress() {
		String macAddres = "";
		InetAddress ip;
		try {

			ip = InetAddress.getLocalHost();
			NetworkInterface network = NetworkInterface.getByInetAddress(ip);
			byte[] mac = network.getHardwareAddress();
			if(mac == null) {
				mac = new byte[0];
			}
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < mac.length; i++) {
				sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
			}			
			macAddres = sb.toString();

		} catch (UnknownHostException e) {

			e.printStackTrace();

		} catch (SocketException e) {

			e.printStackTrace();

		}
		return macAddres;
	}

	public static String obtenerIPAddress() {
		String ipAddress = "";
		InetAddress ip;
		try {

			ip = InetAddress.getLocalHost();
			ipAddress = ip.getHostAddress();

		} catch (UnknownHostException e) {

			e.printStackTrace();

		}
		return ipAddress;
	}
}
