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

package org.craftercms.studio.test.api.objects;

import org.craftercms.studio.test.utils.APIConnectionManager;
import org.craftercms.studio.test.utils.JsonTester;

/**
 * @author luishernandez
 *
 */
public class BaseAPI {
	protected JsonTester api;
	protected APIConnectionManager apiConnectionManager;
	protected String headerLocationBase;

	public BaseAPI(JsonTester api, APIConnectionManager apiConnectionManager) {
		this.api=api;
		this.apiConnectionManager=apiConnectionManager;
		headerLocationBase = this.apiConnectionManager.getHeaderLocationBase();
	}

	public JsonTester getApi() {
		return api;
	}

	public void setApi(JsonTester api) {
		this.api = api;
	}

}
