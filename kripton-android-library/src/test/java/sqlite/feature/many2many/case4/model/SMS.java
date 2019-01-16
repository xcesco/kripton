/*******************************************************************************
 * Copyright 2016-2019 Francesco Benincasa (info@abubusoft.com)
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
package sqlite.feature.many2many.case4.model;

import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class SMS.
 */
public class SMS {
        
        /** The date. */
        public Date date;
        
        /** The from. */
        public String from;
        
        /** The message. */
        public String message;
        
        /** The to. */
        public String to;

        /**
         * Instantiates a new sms.
         *
         * @param paramString1 the param string 1
         * @param paramString2 the param string 2
         * @param paramString3 the param string 3
         * @param paramDate the param date
         */
        public SMS(String paramString1, String paramString2, String paramString3,Date paramDate) {
            this.from = paramString1;
            this.to = paramString2;
            this.message = paramString3;
            this.date = paramDate;
        }
     }
