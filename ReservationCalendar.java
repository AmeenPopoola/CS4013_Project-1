import java.util.ArrayList;
import java.util.GregorianCalendar;

 public class ReservationCalendar {
    private ArrayList<Reservation> reservations = new ArrayList<Reservation>();

    
    /** Fill the array list with reservations from reservations.csv */
    public ReservationCalendar(String[][] resCal, Hotels hotel){
        ArrayList<Room> ro;
        for(int i = 1; i < resCal.length; i++){
            int num = Integer.parseInt(resCal[i][0]);
            int count = 0;

            for(int x = 0; x < reservations.size(); x++){
                if(num == reservations.get(x).getResNumber()){
                    count++;
                }
                
            }
            if(count == 0){
                if(resCal[i][2].equals("Classic Single") || resCal[i][2].equals("Classic Twin") || resCal[i][2].equals("Classic Double")){
                    ro = hotel.getArray("3");
                }else if(resCal[i][2].equals("Executive Single") || resCal[i][2].equals("Executive Twin") || resCal[i][2].equals("ExecutiveDouble")){
                    ro = hotel.getArray("4");
                }else{
                    ro = hotel.getArray("5");
                }
                int resNumber = Integer.parseInt(resCal[i][0]);
                String name = resCal[i][1];
                String roomType = resCal[i][2];
                String dateFrom = resCal[i][3];
                boolean resType = Boolean.parseBoolean(resCal[i][4]);
                int nights = Integer.parseInt(resCal[i][5]);
                int numberOfRooms = Integer.parseInt(resCal[i][6]);
                int numberOfPeople = Integer.parseInt(resCal[i][7]);
                double totalCost = Double.parseDouble(resCal[i][8]);

                Reservation reservation = new Reservation(resNumber, name, roomType, dateFrom, resType, nights, numberOfRooms, numberOfPeople, totalCost);
                int loop = 1;
                for(int j = 0; j < ro.size(); j++){
                    if((roomType.equals(ro.get(j).getRoomType())) == true){
                        if(loop <= numberOfRooms){
                            ro.get(j).getBooked().add(reservation);
                            loop++;
                        }else{
                            reservations.add(reservation);
                            break;
                        }
                    }
                }

            }
        }
    }

    /**Retrieve the reates for the chosen roomType */
    public double[] getRates(Reservation res, Hotels hot){
        for(int i = 0; i < hot.getArray(hot.getChoice()).size(); i++){
            if((res.getRoomType().equals(hot.getArray(hot.getChoice()).get(i).getRoomType())) == true){
                return hot.getArray(hot.getChoice()).get(i).getRates();
            }
        }
        return null;
    }
    /**Cancel a reservation and remove it from the reservation list
     * and removes it from the rooms
     */

     public boolean cancel(int resNumber, Hotels hot, HotelSystem hsys){
        GregorianCalendar greg = new GregorianCalendar();
		boolean flip = false;
		Reservation cancelled = null;
		for(int i = 0; i < hot.getArray(hot.getChoice()).size(); i++){
			for(int j = 0; j < hot.getArray(hot.getChoice()).get(i).getBooked().size(); j++){
				if(resNumber == hot.getArray(hot.getChoice()).get(i).getBooked().get(j).getNumber()){
					if(hot.getArray(hot.getChoice()).get(i).getBooked().get(j).isResType()){
						if((hot.getArray(hot.getChoice()).get(i).getBooked().get(j).getDateFrom().getTimeInMillis() - greg.getTimeInMillis()) > 172800000 ){
							cancelled = hot.getArray(hot.getChoice()).get(i).getBooked().get(j);
							hot.getArray(hot.getChoice()).get(i).getBooked().remove(j);
							flip = true;

						}else{
							System.out.println("Too close to checkin date to cancel!");
							return false;
						}
					}else{
						System.out.println("Not allowed cancel AP reservations!");
						return false;
					}
				}
			}
		}
		
		if(flip == false){
			return false;
		}
		hsys.getCheckData().getCancellations().add(cancelled);
		hsys.getRead().writeCancellations(hot, hsys.getCheckData());
		for(int i = 0; i < reservations.size(); i++){
			if(reservations.get(i).getResNumber() == resNumber){
				if((reservations.get(i).getFrom().getTimeInMillis() - greg.getTimeInMillis()) > 172800000 ){
					reservations.remove(i);
				}
			}
		}
		return true;
	}
    
	public ArrayList<Reservation> getReservations() {
		return reservations;
	}
}