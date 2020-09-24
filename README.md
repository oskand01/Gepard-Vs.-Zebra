# Gepard-Vs.-Zebra
Projektarbete

Gjort en del ändringar för att få det mer Objektorienterat och mindre rörigt. 
Allt som har med djur att göra ligger i Djur, allt om game i Game osv.
Klassen SpelPlan är tillbaka, för att kunna använda metoder på objektet SpelPlan vilket är rätt smidigt märkte jag. Point är perfekt för att hålla koll på, ändra och jämföra positioner och har lagt till det på djuren. https://docs.oracle.com/javase/7/docs/api/java/awt/Point.html

Alla geparder får id 1-10 (maxantalet just nu och går att ändra) och alla zebror får id 11-30.
Arrayen som sparar djuren ligger i interface så att alla klasser har tillgång till den, men funderar på om det kanske borde vara var sin array för geparder och zebror?

Går tyvärr inte att skriva ut djuren på spelplanen i denna varianten än så länge i denna men borde gå att fixa.

OBS! Det är den som heter com.company
