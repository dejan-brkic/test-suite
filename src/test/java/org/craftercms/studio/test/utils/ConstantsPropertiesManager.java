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

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConstantsPropertiesManager {
	private Properties sharedExecutionConstants;

	public ConstantsPropertiesManager(String filePath) {

		this.sharedExecutionConstants = new Properties();

		try {

			sharedExecutionConstants.load(new FileInputStream(filePath));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Properties getSharedExecutionConstants() {
		return sharedExecutionConstants;
	}

	public void setSharedExecutionConstants(Properties sharedExecutionConstants) {
		this.sharedExecutionConstants = sharedExecutionConstants;
	}

	public void setProperty(String key, String value, String filePath) {

		FileOutputStream fileOutputStream;
		try {
			fileOutputStream = new FileOutputStream(filePath);
			this.sharedExecutionConstants.setProperty(key, value);
			this.sharedExecutionConstants.store(fileOutputStream, null);
			fileOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
