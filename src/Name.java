import java.util.ArrayList;

public class Name {

    private String thisName;
    private ArrayList<Debt> debtsOwed;
    //constructor
    public Name(String name){
        thisName = name;
        debtsOwed = new ArrayList<Debt>();
    }

//______________________________________________________________________________________________________________________

    public void printTotals(){
        debtsOwed.forEach((n) -> n.printDebt());
    }

//______________________________________________________________________________________________________________________

    public void addDebt(float debt, String owner, String curName, String curNameNot){
        boolean alreadyExists = false;

        if(curName.equals(thisName)){

            for(int i = 0; i != (debtsOwed.size()); i++){
                Debt cur = debtsOwed.get(i);
                if (cur.checkForExistance(thisName, owner, debt)){
                    alreadyExists = true;
                }
            }

            if (!alreadyExists){
                Debt newDebt = new Debt(debt, owner, thisName);
                debtsOwed.add(newDebt);
            }
        }

        if(!curNameNot.equals("") && !curNameNot.equals(thisName)){

            for(int i = 0; i != (debtsOwed.size()); i++){
                Debt cur = debtsOwed.get(i);
                if (cur.checkForExistance(thisName, owner, debt)){
                    alreadyExists = true;
                }
            }

            if (!alreadyExists){
                Debt newDebt = new Debt(debt, owner, thisName);
                debtsOwed.add(newDebt);
            }
        }

        else if(curName.equals("") && curNameNot.equals("")){

            for (int i = 0; i != (debtsOwed.size()); i++) {
                Debt cur = debtsOwed.get(i);
                if (cur.checkForExistance(thisName, owner, debt)) {
                    alreadyExists = true;
                }
            }

            if (!alreadyExists) {
                Debt newDebt = new Debt(debt, owner, thisName);
                debtsOwed.add(newDebt);
            }
        }
    }


}
