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
package com.pogofish.jadt.target;

/**
 * A target represents a location to emit java
 *
 * @author jiry
 */
public interface Target {
	/**
	 * Information about where this target is going
	 */
	public String getInfo();

    /**
     * Close this target.  Any further writes will result in an exception
     */
    public abstract void close();

    /**
     * Write data to this target
     * 
     * @param data String to write
     */
    public abstract void write(String data);

}
