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


/**
 * This exception is a generic kripton exception. 
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public class KriptonRuntimeException extends RuntimeException {
	
	/** The detail. */
	protected Throwable detail;
    
    /** The row. */
    protected int row = -1;
    
    /** The column. */
    protected int column = -1;
    
    /**
     * Gets the detail.
     *
     * @return the detail
     */
    public Throwable getDetail() { return detail; }
    
    /**
     * Gets the line number.
     *
     * @return the line number
     */
    //    public void setDetail(Throwable cause) { this.detail = cause; }
    public int getLineNumber() { return row; }
    
    /**
     * Gets the column number.
     *
     * @return the column number
     */
    public int getColumnNumber() { return column; }

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7993865639459660322L;

	/**
	 * Instantiates a new kripton runtime exception.
	 */
	public KriptonRuntimeException() {
	}

	/**
	 * Instantiates a new kripton runtime exception.
	 *
	 * @param arg0 the arg 0
	 */
	public KriptonRuntimeException(String arg0) {
		super(arg0);
	}

	/**
	 * Instantiates a new kripton runtime exception.
	 *
	 * @param arg0 the arg 0
	 */
	public KriptonRuntimeException(Throwable arg0) {
		super(arg0);
	}

	/**
	 * Instantiates a new kripton runtime exception.
	 *
	 * @param arg0 the arg 0
	 * @param arg1 the arg 1
	 */
	public KriptonRuntimeException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}
	
	 /**
 	 * Instantiates a new kripton runtime exception.
 	 *
 	 * @param msg the msg
 	 * @param parser the parser
 	 * @param row the row
 	 * @param col the col
 	 * @param description the description
 	 * @param chain the chain
 	 */
 	public KriptonRuntimeException(String msg, boolean parser, int row, int col, String description, Throwable chain) {
	        super ((msg == null ? "" : msg+" ")
	               + (description == null ? "" : "(position:"+description+") ")
	               + (chain == null ? "" : "caused by: "+chain));

	        if (parser) {
	            this.row = row;
	            this.column = col;
	        }
	        this.detail = chain;
	    }

}
