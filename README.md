# Stín nad Hradem Eldor

**Autor**: Jakub Moravec  
**Datum vytvoření**: 15. dubna 2025

## Stručný popis hry

*Stín nad Hradem Eldor* je textová dobrodružná hra, kde hráč hraje za hrdinu, který má za úkol zabít temného mága Malagarna. Hráč se pohybuje mezi různými místnostmi hradu, interaguje s NPC postavami a sbírá předměty. Hra je postavena na systém boje, který zahrnuje útoky, obranu, útěk a léčbu. Hráč se rozhoduje, jakým způsobem se bude vypořádávat s překážkami a nepřáteli na své cestě.

## Jak spustit hru

1. **Požadavky**:
    - **Java Development Kit (JDK)** verze 11 nebo novější.
    - **IDE (např. IntelliJ IDEA, Eclipse)** pro spuštění a úpravy kódu.

2. **Spuštění**:
    - Stáhni a rozbal složku projektu.
    - V IDE otevři projekt a spusť soubor `Main.java` (nebo `StinNadHrademEldor.java`, pokud máš jiný název třídy).
    - Pokud chceš vytvořit spustitelný JAR soubor, použij následující příkaz v příkazové řádce:
        ```bash
        javac StinNadHrademEldor.java
        jar cfe StinNadHrademEldor.jar StinNadHrademEldor StinNadHrademEldor.class
        java -jar StinNadHrademEldor.jar
        ```

3. **Nástroje**:
    - **JDK 11 nebo novější** (JDK 17 je doporučeno).
    - Pokud používáš IDE jako IntelliJ IDEA nebo Eclipse, měly by ti automaticky nabídnout JDK potřebné pro kompilaci a běh aplikace.

## Použité knihovny a frameworky

- **Java Standard Library** – pro práci se soubory, textovým vstupem a výstupem.
- **Java.util.concurrent** – pro implementaci synchronizace v rámci vícevláknového boje.
- **com.google.gson.Gson** - pro nacitani z jsonu

## Jak hra funguje

Hráč začne ve vstupní hale hradu a musí se rozhodnout, jaký bude jeho další krok. Může se rozhodnout mluvit s NPC, sbírat předměty nebo bojovat s nepřáteli. Každá volba ovlivňuje výsledek příběhu.

### Hlavní mechaniky:
- **Boj**: Systém boje je založen na šanci na zásah, kritických zásazích a použití předmětů. Hráč má možnost útočit, bránit se, utéct nebo použít lektvary k vyléčení.
- **Předměty**: Hráč může sbírat předměty, které ovlivňují jeho schopnosti (např. zbraně, lékárničky).
- **NPC**: Ve hře se nacházejí různé postavy, které mohou hráči nabídnout pomoc nebo ho ovlivnit negativně.
- **Soubojový systém**: Boj je dynamický a závisí na rychlosti postav, což ovlivňuje pořadí tahů.

## Jaký je cíl hry

Cílem hry je porazit temného mága Malagarna, který ohrožuje celý hrad a jeho obyvatele. Hráč se musí připravit na náročné souboje a rozhodování, jakým způsobem bude postupovat k finálnímu souboji.

## Konfigurovatelnost

Herní svět (místnosti, postavy, předměty) je načítán ze souboru, takže je možné snadno přidávat nové místnosti a postavy bez nutnosti měnit kód.

Herní data jsou načítána v textovém formátu, kde každá místnost, postava a předmět má svůj definovaný popis, lokaci a vlastnosti.

## Ošetření chyb

- **Neplatné vstupy**: Hra automaticky ošetřuje neplatné příkazy zadané hráčem a poskytuje zpětnou vazbu, aniž by došlo k pádu aplikace.
- **Načítání dat**: Pokud dojde k chybě při načítání dat (např. neexistující soubor), hráč dostane informaci o problému.

## Dokumentace kódu

Veškerý kód je podrobně okomentován pomocí **JavaDoc** formátu, takže pokud používáš IDE, jako je IntelliJ IDEA, všechny třídy a metody by měly být snadno pochopitelné.

---

Pokud máš nějaké dotazy ohledně hry nebo potřebuješ pomoc, neváhej mě kontaktovat!

**Jakub Moravec**
