# Sweet Suite - צד שרת (Backend)

## 🖥️ סקירה כללית
הצד של השרת בפרויקט **Sweet Suite** נבנה בשפת **Java** באמצעות **Spring Boot**, עם דגש על אבטחה, ביצועים ותחזוקה נוחה.

### טכנולוגיות וכלים:
- **Java** 
- **Spring Boot** 
- **H2** 
- **MapStruct:** 
- **JWT**
- **REACT-REDUX**

### מאפיינים מרכזיים:
1. **ניהול משתמשים:**
   - **רישום משתמשים:** רישום משתמשים חדשים עם פרטי משתמש והעלאת תמונת פרופיל.
   - **התחברות:** התחברות מאובטחת באמצעות JWT.
   - **ניהול פרופיל:** מאפשר למשתמשים לעדכן ולנהל את הפרופיל האישי שלהם.

2. **סינון סוויטות:**
   - **סינון סוויטות:** אפשרות לסנן סוויטות על פי פרמטרים שונים:
     - מספר מיטות (`numberBeds`).
     - חניה (`parking`).
     - נוף לים (`seaView`).
     - מחיר ללילה (`pricePerNight`).
   - הסינונים מתבצעים בצד הלקוח (React) ומעודכנים בזמן אמת.

3. **ביקורות ודירוגים:**
   - **כתיבת ביקורות:** למשתמשים יש אפשרות לכתוב ביקורות על סוויטות.
   - **קריאת ביקורות:** למשתמשים יש אפשרות לקרוא ביקורות ודירוגים של לקוחות אחרים.


5. **אבטחה:**
   - **אימות באמצעות JWT:** ניהול מאובטח של סשנים והגנה על המידע מפני גישה לא מורשית.


