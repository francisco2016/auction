import java.util.ArrayList;
import java.util.Iterator;

/**
 * A simple model of an auction.
 * The auction maintains a list of lots of arbitrary length.
 *
 * @author David J. Barnes and Michael Kölling.
 * @version 2011.07.31
 */
public class Auction
{
    // The list of Lots in this auction.
    private ArrayList<Lot> lots;
    // The number that will be given to the next lot entered
    // into this auction.
    private int nextLotNumber;

    /**
     * Create a new auction.
     */
    public Auction()
    {
        lots = new ArrayList<Lot>();
        nextLotNumber = 1;
    }

    /**
     * Enter a new lot into the auction.
     * @param description A description of the lot.
     */
    public void enterLot(String description)
    {
        lots.add(new Lot(nextLotNumber, description));
        nextLotNumber++;
    }

    /**
     * Show the full list of lots in this auction.
     */
    public void showLots()
    {
        for(Lot lot : lots) {
            System.out.println(lot.toString());
        }
    }

    /**
     * Make a bid for a lot.------------------------------------------------------------------------------ 0059
     * A message is printed indicating whether the bid is
     * successful or not.
     * 
     * @param lotNumber The lot being bid for.
     * @param bidder The person bidding for the lot.
     * @param value  The value of the bid.
     */
    public void makeABid(int lotNumber, Person bidder, long value)//--------------------------------- 0059
    {
        Lot selectedLot = getLot(lotNumber);
        if(selectedLot != null) {

            boolean successful = selectedLot.bidFor(new Bid(bidder, value));
            if(successful) {
                System.out.println("The bid for lot number " +
                    lotNumber + " was successful.");
            }
            else {
                // Report which bid is higher.

                System.out.println("Lot number: " + lotNumber +
                    " already has a bid of: " +
                    selectedLot.getHighestBid().getValue());
            }
        }
    }

    /**
     * Este m�todo   muestrar por pantalla los detalles de todos los items que se est�n subastando actualmente. 
     * De aquellos por los que haya habido pujas se debe indicar el nombre de la persona que ha hecho la puja m�s alta
     * y el valor de dicha puja; del resto debe indicar que no ha habido pujas.-------------------------- 0060
     * -----------------------------------------------------------------------------------------   0060
     */
    public void close(){
        for(Lot lot : lots){
            System.out.println(lot);//la cl. Lot tiene el mt toString(); por lo que java imprime, los datos, incocando
            // a este mt, sobre la VL lot en la que hemos almacenado ,,,,,,,
            if(lot.getHighestBid() != null){
                System.out.println("Puja m�s alta, hecha por: " +lot.getHighestBid().getBidder().getName());
                System.out.println("Con un valor de: " +lot.getHighestBid().getValue());
            }

        }
    }

    /**
     * devuelva una colecci�n de todos los items por los que no habido ninguna puja en este momento; este m�todo
     * no debe imprimir nada por pantalla. ----
     * ------------------------------------------------------------------------------------------------------ 0061
     */
    public ArrayList<Lot> getUnslod(){
        ArrayList<Lot> noSubastado = new ArrayList<>();
        for(Lot lot : lots){
            if(lot.getHighestBid() == null ){
                noSubastado.add(lot) ;
            }
        }
        return noSubastado;
    }
    
    /**
     * recibe como par�metro un entero que represente el n�mero identificador de un item y elimina dicho item de
     * la colecci�n de items.  -------------------------------------------------------------------------- 0062
     * No se puede asumir que un item n estar� en la posici�n n-1 por la posibilidad de que haya borrado de elementos. 
     * Este m�todo debe devolver el elemento eliminado o null en caso de que dicho elemento no exista.
     * -------------------------------------------------------------------------------------------------------- 0062
     */
    public Lot removeLot(int number){
        Lot lot = getLot(number);
        if(lot != null) {
            lots.remove(lot);
        }
        return lot;
    }

    
     public Lot getLot(int lotNumber)
    {
        Lot selectedLot = null;
        boolean encontrado = false;
        int index = 0;
        while(index < lots.size() && !encontrado) {          
            if(lots.get(index).getNumber() == lotNumber) {
                selectedLot = lots.get(index);
                encontrado = true;
            } 
            index ++;
        }
        if(!encontrado) {
            System.out.println("Lot number: " + lotNumber +
                " does not exist.");
            selectedLot = null;
        }
        return selectedLot; 
    }
    
}
