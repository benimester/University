# Model:
### Model.java
    - Az alkalmazás futásának a modellje
    - Létrehozza a kommunikációt a View és a Control között
### Track.java 
    A hangfájlokhoz tartozó információk összesítő osztálya
### AudioFileManager.java 
    - Tetszőleges katalógusból kiszűri a hangfájlokat
    - Kiolvassa a rendelkezésre álló metadata-t (JAudioTagger)
    - Előállít egy duplán láncolt listát a hangfájlokból
### State.java
    Egy snapshotot tárol a lejátszóról

# View:
### View.java
    Megjelenítésért felelős osztály
### HomeScreen.java
    - A lejátszó komponenseit létrehozó osztály
    - Frissíti a megjelenítést
### MyPlayer.java
    - A lejátszó irányításához tartozó grafikus megjelenítés
    - Play/Pause/Previous/Next
### MetaData.java
    Megjeleníti a hangfájl "adatlapját" 
### MusicList.java
    A soron következő hangfájlok listáját jeleníti meg
### ITheme.java
    Interfész amellyel témákat lehet implementálni
### LightTheme.java
    Egy világos téma
### DarkTheme.java
    Egy sötét téma

# Control: 
### Control.java
    Központi irányítás
### PlayerController.java
    - A kétszeresen láncolt listában navigál a gomblenyomások hatására
    - A zene lejátszását egy új szálra helyezi

### Streamek használata:
    - MusicList.java - loadTracks()
    - AudioFileManager.java - AudioFileManager()
    - Model.java - choosetheme()