/*     */ package cn.ac.ihep.pmtfts.actions;
/*     */ 
/*     */ import java.util.Date;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;

/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.NamedQueries;
/*     */ import javax.persistence.OneToMany;
import javax.persistence.Table;
/*     */ @Entity
/*     */ @Table(name = "test")
/*     */ //@NamedQueries({@javax.persistence.NamedQuery(name="getLastWatched", query="SELECT m FROM Test m WHERE m.role = :role")})
/*     */ public class Test
/*     */ {
/*     */   private int lastWatchedKey;
/*     */   private Date lastDateTime;

/*     */   private String role;
/*     */   //private String filestate;
/*     */   protected Test() {}
/*     */   
Test(String role, Date dateTime)
/*     */   {
/*  82 */     setRole(role);
/*  83 */     setLastDateTime(dateTime);

/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue
/*     */   protected int getLastWatchedKey()
/*     */   {
/*  99 */     return lastWatchedKey;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @Column(nullable=false)
/*     */   public Date getLastDateTime()
/*     */   {
/* 109 */     return lastDateTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 

/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @Column(nullable=false, updatable=false)
/*     */   public String getRole()
/*     */   {
/* 135 */     return role;
/*     */   }

/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void setLastWatchedKey(int key)
/*     */   {
/* 146 */     lastWatchedKey = key;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void setLastDateTime(Date lastDateTime)
/*     */   {
/* 156 */     this.lastDateTime = lastDateTime;
/*     */   }
/*     */   
/*     */   protected void setRole(String role)
/*     */   {
/* 180 */     this.role = role;
/*     */   }

/*     */ }

/* Location:           G:\Java\sentry\target\sentry-1.0.0\WEB-INF\lib\nest-jee-1.0.1.jar
 * Qualified Name:     gov.lbl.nest.jee.watching.LastWatched
 * Java Class Version: 8 (52.0)
 * JD-Core Version:    0.7.1
 */