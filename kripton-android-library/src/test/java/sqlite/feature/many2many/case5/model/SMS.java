package sqlite.feature.many2many.case5.model;

import java.util.Date;

public class SMS {
        public Date date;
        public String from;
        public String message;
        public String to;

        public SMS(String paramString1, String paramString2, String paramString3,Date paramDate) {
            this.from = paramString1;
            this.to = paramString2;
            this.message = paramString3;
            this.date = paramDate;
        }
     }