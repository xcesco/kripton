package bind.feature.kotlin.case1;

import com.abubusoft.kripton.annotation.BindType;

@BindType
public final class Article {
   private final long id;

   public final long getId() {
      return this.id;
   }

   public Article(long id) {
      this.id = id;
   }

   public final long component1() {
      return this.id;
   }

   public final Article copy(long id) {
      return new Article(id);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public static Article copy$default(Article var0, long var1, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var1 = var0.id;
      }

      return var0.copy(var1);
   }

   public String toString() {
      return "Article(id=" + this.id + ")";
   }

   public int hashCode() {
      return (int)(this.id ^ this.id >>> 32);
   }

   public boolean equals(Object var1) {
      if (this != var1) {
         if (var1 instanceof Article) {
            Article var2 = (Article)var1;
            if (this.id == var2.id) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }
}