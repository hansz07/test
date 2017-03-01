package annotation;

  public enum Gender {
     MAN{
         public String getName(){return "男";}
     },
     WOMEN{
         public String getName(){return "女";}
     }; //记得有“;”
      public abstract String getName();
 }