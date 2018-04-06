/*******************************************************************************
 * Copyright 2018 Francesco Benincasa (info@abubusoft.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package sqlite.kripton186.model;

import com.abubusoft.kripton.annotation.BindType;

// TODO: Auto-generated Javadoc
/**
 * The Class PrefixConfig.
 */
@BindType
public class PrefixConfig {

    /** The id. */
    public long id;

    /** The default country. */
    public String defaultCountry;

    /** The dual billing prefix. */
    public String dualBillingPrefix;

    /** The enabled. */
    public boolean enabled;

    /** The dialog timeout. */
    public long dialogTimeout;
}
