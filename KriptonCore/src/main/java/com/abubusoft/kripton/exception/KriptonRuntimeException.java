/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.abubusoft.kripton.exception;

import com.abubusoft.kripton.xml.XmlPullParser;

/**
 * This exception is a generic kripton exception. 
 * 
 * @author xcesco
 *
 */
public class KriptonRuntimeException extends RuntimeException {
	
	protected Throwable detail;
    protected int row = -1;
    protected int column = -1;
    
    public Throwable getDetail() { return detail; }
    //    public void setDetail(Throwable cause) { this.detail = cause; }
    public int getLineNumber() { return row; }
    public int getColumnNumber() { return column; }

	private static final long serialVersionUID = 7993865639459660322L;

	public KriptonRuntimeException() {
	}

	public KriptonRuntimeException(String arg0) {
		super(arg0);
	}

	public KriptonRuntimeException(Throwable arg0) {
		super(arg0);
	}

	public KriptonRuntimeException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}
	
	 public KriptonRuntimeException(String msg, XmlPullParser parser, Throwable chain) {
	        super ((msg == null ? "" : msg+" ")
	               + (parser == null ? "" : "(position:"+parser.getPositionDescription()+") ")
	               + (chain == null ? "" : "caused by: "+chain));

	        if (parser != null) {
	            this.row = parser.getLineNumber();
	            this.column = parser.getColumnNumber();
	        }
	        this.detail = chain;
	    }

}
