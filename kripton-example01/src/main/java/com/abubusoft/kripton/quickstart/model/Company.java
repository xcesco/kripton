/*******************************************************************************
 * Copyright (c) 2017 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package com.abubusoft.kripton.quickstart.model;

import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Company {

    public String name;
    public String catchPhrase;
    public String bs;

    @Override
    public String toString() {
        return name;
    }
}