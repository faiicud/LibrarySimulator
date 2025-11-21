package t;
import java.util.Scanner;

// Member of our group : Talal-Lafi & Faisal-Albishi & Abdullah-Alutaibi
// اسم الكلاس يجب أن يطابق اسم الملف المطلوب تسليمه
public class LibrarySimulator { 
    public static void main(String args[]) {  
        Scanner input = new Scanner(System.in);
        
        // --- 1. إنشاء الكائنات (Objects) ---
        // بدلاً من كل المتغيرات القديمة، ننشئ 3 كائنات فقط
        
        final int ID_1 = 1;
        final String UserName1 = "Faisal";
        Member member1 = new Member(ID_1, UserName1, 0); // كائن العضو الأول
        
        final int ID_2 = 2;
        final String UserName2 = "Talal";
        Member member2 = new Member(ID_2, UserName2, 0); // كائن العضو الثاني

        final int ID_3 = 3;
        final String UserName3 = "Abdullah";
        Member member3 = new Member(ID_3, UserName3, 0); // كائن العضو الثالث

        // --- تم حذف جميع متغيرات الإحصائيات العامة ---
        // لأنها موجودة الآن داخل كلاس Member (مثل Member.TotalRevenue)
        
        boolean running = true;
        
        // Main menu
        while(running) {
            System.out.println(" ------Welcome to The Library Simulator------");
            System.out.println("1. Login As "+ UserName1 + " (ID: "+ ID_1+ ")");
            System.out.println("2. Login As "+ UserName2 + " (ID: "+ ID_2+ ")");
            System.out.println("3. Login As "+ UserName3 + " (ID: "+ ID_3+ ")");
            System.out.println("4. Login As Administrator");
            System.out.println("5. Exit The Program");
            System.out.print("Choose An Option boyy : ");
            int choice = input.nextInt();
            input.nextLine(); // Cosume newLine
            
            // --- 2. تبسيط عملية "تسجيل الدخول" ---
            // بدلاً من نسخ 10 متغيرات، نمرر "مرجع" للكائن الحالي فقط
            Member currentMember = null; // سيشير هذا إلى العضو الذي سجل الدخول
            
            if (choice >= 1 && choice <= 3) {
                // Load user data based on choise
                if (choice == 1) {
                    currentMember = member1;
                } else if (choice == 2) {
                    currentMember = member2;
                } else if (choice == 3) {
                    currentMember = member3;
                }

                boolean sessionActive = true;
                while (sessionActive) {
                    // نستخدم Getter للحصول على الاسم من الكائن مباشرة
                    System.out.println("\n--- Welcome "+ currentMember.getName() + "----"); 
                    System.out.println("1. View Borrowed Books Count");
                    System.out.println("2. Borrow A Book");
                    System.out.println("3. Return A Book");
                    System.out.println("4. View Session Summary");
                    System.out.println("5. Get Back TO Main Menu");
                    System.out.print("Choose An Option : ");
                    int userOption = input.nextInt();
                    input.nextLine(); // Consume newline

                    // --- 3. تبسيط العمليات (Switch) ---
                    // كل case أصبح يستدعي دالة واحدة فقط من كلاس Member
                    switch (userOption) {
                        case 1:
                            // View Current Borrowed Books
                            currentMember.viewBorrowedCount(); // الكائن يقوم بالطباعة وتحديث الإحصائيات
                            break;
   
                        case 2:
                            // Borrowing a book
                            currentMember.borrowOne(); // الكائن يقوم بكل شيء: التحقق، الخصم، تحديث الإحصائيات
                            break; // (ملاحظة: أضفت 'break' هنا، كانت مفقودة في كودك الأصلي)

                        case 3:
                            // Returning a book
                            currentMember.returnOne(); // الكائن يقوم بالتحقق وتحديث الإحصائيات
                            break;
                            
                        case 4:
                            // Session Summary
                            currentMember.displayStatistics(); // الكائن يطبع إحصائياته الخاصة
                            break;
                            
                        case 5:
                            // --- 4. حذف عملية "تسجيل الخروج" المعقدة ---
                            // لا نحتاج لحفظ البيانات، لأن التعديلات تمت على الكائن الأصلي مباشرة
                            sessionActive = false; // session Loop
                            System.out.println("Logging out and returning to the Main Menu");
                            break;	
                        
                        default:
                            System.out.println("Invalid Option. Try Again.");
                    }
                }
            }
            else if (choice == 4) {
                // Administrator Login
                System.out.print("Enter Admin Password: ");
                String password = input.nextLine(); 
                
                if (password.equals("Admin")) { // (الأفضل "Admin" وليس "Admin ")
                    // --- 5. تحديث لوحة التحكم ---
                    // نقرأ الإحصائيات العامة مباشرة من كلاس Member
                    System.out.println("\n--- Administrator Panel ---");
                    System.out.printf("Total Library Revenue: $%.2f\n", Member.TotalRevenue);
                    System.out.println("Total Books Borrowed (Global): " + Member.TotalBorrows);
                    System.out.println("Total Books Returned (Global): " + Member.TotalReturns);
                    System.out.println("Total 'View Count' (Global): " + Member.TotalViewBorrowed); // إحصائية جديدة
                    
                    System.out.println("\n--- User Statistics ---");
                    // (نفترض أن لديك getters في كلاس Member كما في ردي السابق)
                    System.out.println(member1.getName() + " (ID:" + member1.getId() + "): Currently Borrowed = " + member1.getBorrowedCount());
                    System.out.println(member2.getName() + " (ID:" + member2.getId() + "): Currently Borrowed = " + member2.getBorrowedCount());
                    System.out.println(member3.getName() + " (ID:" + member3.getId() + "): Currently Borrowed = " + member3.getBorrowedCount());
                
                } else {
                    System.out.println("Incorrect Password");
                }
            }
            else if (choice == 5) {
                System.out.println("Thank u and see ya later boyys");
                running = false; // Exit The Main loop
            }
            else {
                System.out.println("Invalid Option. Please choose between 1 and 5.");
            }
        }
        input.close();
    }
    
}
