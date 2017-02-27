SELECT a.log AS x , a.ss FROM t1.a 
                GROUP BY x                   
                HAVING count(*) >= 4                         
                ORDER BY max(n) + 0         