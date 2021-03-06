/*
Copyright 2012 James Iry

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package com.pogofish.jadt;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.Properties;

import com.pogofish.jadt.util.ExceptionAction;
import com.pogofish.jadt.util.Util;


/**
 * Information about the version of jADT being used
 * 
 * @author jiry
 */
public class Version {
    /** not a constant to allow for testing */
	String MODULE_PROPERTIES = "module.properties";
	
	/** not a constant to allow for testing */
	String MODULE_VERSION = "module.version";

	public String getVersion() {
		return Util.execute(new ExceptionAction<String>() {

			@Override
			public String doAction() throws IOException {
				final URL resource = Version.class.getClassLoader().getResource(MODULE_PROPERTIES);
				if (resource == null) {
				    throw new FileNotFoundException("Could not find internal resource " + MODULE_PROPERTIES);
				}
				final Reader reader = new InputStreamReader(
						resource.openStream(), "UTF-8");
				try {
					final Properties properties = new Properties();
					properties.load(reader);
					final String property = properties.getProperty(MODULE_VERSION);
					return property == null ? "unknown version, could not find property " + MODULE_VERSION + " in " + MODULE_PROPERTIES : property;
				} finally {
					reader.close();
				}

			}
		});
	}
}
