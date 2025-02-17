# Vending Machine GUI Java Prog2

Ett projekt i Programmering 2 där jag har skapat en varuautomat med Java Swing.

## UML-diagram Notation
| Symbol | Betydelse | Exempel |
|--------|-----------|---------|
| `-` | Private medlemmar | `-name : String` |
| `+` | Public medlemmar | `+getName() : String` |
| `*` | Abstrakt metod | `+getType()** : String` |
| `<<abstract>>` | Abstrakt klass | `<<abstract>> Produkt` |

## UML-diagram
```mermaid
classDiagram
    class vendingMachineGUI {
        -VMLogik logic
        -JButton[] productbuttons
        +vendingMachineGUI()
    }
    class VMLogik {
        -List~Produkt~ products
        +buyProduct(int) : Produkt
        +getProducts() : List
    }
    class filehandler {
        +loadProductsFromCSV() : List
        +saveState(VMLogik)
        +loadState() : VMLogik
    }
    class Produkt {
        <<abstract>>
        -name : String
        -price : double
        -quantity : int
        -taxRate : double
        +getType()** : String
    }
    class Drink {
        +getType() : String
    }
    class Snack {
        +getType() : String
    }
    class Pocketbok {
        +getType() : String
    }
    Produkt <|-- Drink : ärver
    Produkt <|-- Snack : ärver
    Produkt <|-- Pocketbok : ärver
    vendingMachineGUI --> VMLogik : använder
    VMLogik --> Produkt : har många
    VMLogik ..> filehandler : använder
    vendingMachineGUI ..> filehandler : använder
