/*
 * Copyright (C) 2007-2019 Crafter Software Corporation. All Rights Reserved.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.craftercms.studio.test.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.dumbster.smtp.SimpleSmtpServer;
import com.dumbster.smtp.SmtpMessage;

/**
 * @author luishernandez
 *
 */
public class FakeSMTPServerManager {

	private int port;
	private SimpleSmtpServer simpleSMTPServer;

	public FakeSMTPServerManager() {
		ConstantsPropertiesManager constantsPropertiesManager = new ConstantsPropertiesManager(
				FilesLocations.CONSTANTSPROPERTIESFILEPATH);
		port = Integer
				.parseInt(constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.api.smtpport"));
	}

	public void startFakeSMTPServer() {
		try {
			simpleSMTPServer = SimpleSmtpServer.start(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void stopFakeSMTPServer() {
		simpleSMTPServer.stop();
	}

	public List<SmtpMessage> getMessages() {
		// adding some timing to get the received email with token
		try {
			Thread.sleep(4);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return simpleSMTPServer.getReceivedEmails();
	}

	public String getRecentlyGeneratedToken() {
		String token = "";

		Pattern pattern = Pattern.compile("token=([^\\\"]+)");
		Matcher matcher = pattern.matcher(this.getMessages().get(0).getBody());
		if (matcher.find()) {
			try {
				token = URLDecoder.decode(matcher.group(1), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}

		return token;
	}
}
