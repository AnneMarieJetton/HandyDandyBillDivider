public class Debt {

    private float thisDebt;
    private String thisOwner;
    private String thisName;

    public Debt(float debt, String owner, String name){
        thisDebt = debt;
        thisOwner = owner;
        thisName = name;
    }

//______________________________________________________________________________________________________________________

    public void printDebt(){

        if (!thisName.equals(thisOwner)){
            String printable = thisName + " owes " + thisOwner + " " + thisDebt;
            System.out.println(printable);
        }
    }

//______________________________________________________________________________________________________________________

    private void update(float debt){
        thisDebt = thisDebt + debt;
    }

//______________________________________________________________________________________________________________________

    public boolean checkForExistance(String checkName, String checkOwner, float debt){
        boolean found = false;

        if (checkName.equals(thisName) && checkOwner.equals(thisOwner)){
             update(debt);
             found = true;
        }

        return found;
    }
}
