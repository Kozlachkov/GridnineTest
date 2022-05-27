package com.GridnineTest1;

import com.GridnineTest1.model.*;
import com.GridnineTest1.model.flight.Carrier;
import com.GridnineTest1.model.flight.Legs;
import com.GridnineTest1.model.flight.Price;
import com.GridnineTest1.model.flight.PriceClass.Total;
import com.GridnineTest1.model.legs.ArrivalCity;
import com.GridnineTest1.model.legs.DepartureCity;
import com.GridnineTest1.model.legs.Segments;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.Collections;

public class JsonSimpleParser {
    public static final String TAG_RESULT = "result";
    public static final String TAG_FLIGHTS = "flights";
    public static final String TAG_FLIGHT = "flight";
    public static final String TAG_CARRIER = "carrier";
    public static final String TAG_CARRIER_CAPTION = "caption";
    public static final String TAG_PRICE = "price";
    public static final String TAG_TOTAL_PRICE = "total";
    public static final String TAG_TOTAL_AMOUNT = "amount";
    public static final String TAG_LEGS = "legs";
    public static final String TAG_DURATION = "duration";
    public static final String TAG_SEGMENTS = "segments";
    public static final String TAG_DEPARTURE_CITY = "departureCity";
    public static final String TAG_CITY_CAPTION = "caption";
    public static final String TAG_ARRIVAL_CITY = "arrivalCity";

    public static final String DEPARTURE = "москва";
    public static final String DESTINATION = "лондон";
    private static boolean requiredRoute = false;

    public boolean isRequiredRoute() { return requiredRoute; }
    public void setRequiredRoute(boolean requiredRoute) {  this.requiredRoute = requiredRoute;  }

    public Root parseMyProgram(){
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader("flights.json")){
            try {
                JSONObject rootJsonObject = (JSONObject) parser.parse(reader);
                JSONObject resultJsonObject = (JSONObject) rootJsonObject.get(TAG_RESULT);
                //считали все полёты
                JSONArray flightsJsonArray = (JSONArray) resultJsonObject.get(TAG_FLIGHTS);
                List<Flights> fltsList = new ArrayList<>();
                for (Object fls : flightsJsonArray){
                    JSONObject flightsJsonObject = (JSONObject) fls;
                    JSONObject flightJsonObject = (JSONObject) flightsJsonObject.get(TAG_FLIGHT);
                        //ветка carrier & price
                    JSONObject carrierJsonObejct = (JSONObject) flightJsonObject.get(TAG_CARRIER);
                    String carrier_caption = (String) carrierJsonObejct.get(TAG_CARRIER_CAPTION);
                        Carrier carrier = new Carrier(carrier_caption);
                    JSONObject priceJsonObejct = (JSONObject) flightJsonObject.get(TAG_PRICE);
                    JSONObject priceTotalJsonObejct = (JSONObject) priceJsonObejct.get(TAG_TOTAL_PRICE);
                    String price_total_amount = (String) priceTotalJsonObejct.get(TAG_TOTAL_AMOUNT);
                        Price price = new Price(new Total(price_total_amount));
                        //ветка legs
                    JSONArray legsJsonArray = (JSONArray) flightJsonObject.get(TAG_LEGS);
                    List<Legs> lgsList = new ArrayList<>();
                    for (Object lgs : legsJsonArray){
                        JSONObject legsJsonObject = (JSONObject) lgs;
                        Long duration = (Long) legsJsonObject.get(TAG_DURATION);
                        //получаем сегменты
                        JSONArray segmentsJsonArray =  (JSONArray) legsJsonObject.get(TAG_SEGMENTS);
                        List<Segments> sgmntsList = new ArrayList<>();
                        for (Object sgmts : segmentsJsonArray){
                            JSONObject segmentsJsonObject = (JSONObject) sgmts;
                            //города прилета и отлета
                            JSONObject departureCityJsonObject = (JSONObject) segmentsJsonObject.get(TAG_DEPARTURE_CITY);
                            String departureCityCaption;
                            try{ departureCityCaption = (String) departureCityJsonObject.get(TAG_CITY_CAPTION);}
                            catch (NullPointerException e) {departureCityCaption="Здесь была ошибка в файле JSON";}
                            JSONObject arrivalCityJsonObject = (JSONObject) segmentsJsonObject.get(TAG_ARRIVAL_CITY);
                            String arrivalCityCaption;
                            try{ arrivalCityCaption = (String) arrivalCityJsonObject.get(TAG_CITY_CAPTION);  }
                            catch (NullPointerException e) {arrivalCityCaption="Здесь была ошибка в файле JSON";}
                                DepartureCity departureCity = new DepartureCity(departureCityCaption);
                                ArrivalCity arrivalCity = new ArrivalCity(arrivalCityCaption);
                                Segments segments = new Segments(departureCity, arrivalCity);
                                sgmntsList.add(segments);
                        }
                        Legs legs = new Legs(duration,sgmntsList);

                        lgsList.add(legs);
                    }
                    //проверка на совпадение с нашим маршрутом
                    if (lgsList.size()!=2) {
                        System.out.println("количество LEGS не равно двум");
                    }
                    else {
                        //маршрут туда совпадает
                        if (DEPARTURE.toLowerCase().equals(lgsList.get(0).getSegments().get(0).getDepartureCity().getCaption().toLowerCase()) &&
                            DESTINATION.toLowerCase().equals(lgsList.get(0).getSegments().get(lgsList.get(0).getSegments().size()-1).getArrivalCity().getCaption().toLowerCase())) {
                            setRequiredRoute(true);
                        }
                        else setRequiredRoute(false);
                        //маршрут обратно совпадает
                        if (DESTINATION.toLowerCase().equals(lgsList.get(1).getSegments().get(0).getDepartureCity().getCaption().toLowerCase()) &&
                            DEPARTURE.toLowerCase().equals(lgsList.get(1).getSegments().get(lgsList.get(1).getSegments().size()-1).getArrivalCity().getCaption().toLowerCase()) &&
                            isRequiredRoute()) {
                            setRequiredRoute(true);
                        }
                        else setRequiredRoute(false);
                        if (isRequiredRoute()) {
                            Flight flight = new Flight(carrier, price, lgsList);
                            Flights flights = new Flights(flight);
                            fltsList.add(flights);
                        }
                    }
                }
                //сортировка по возрастанию цены
                Comparator<Flights> comparator = new Comparator<Flights>(){
                    public int compare(Flights fl1, Flights fl2) {
                        int price1 = Integer.parseInt(fl1.getFlight().getPrice().getTotal().getAmount());
                        int price2 = Integer.parseInt(fl2.getFlight().getPrice().getTotal().getAmount());
                        return (int) (price1 - price2);
                    }
                };
                Collections.sort(fltsList, comparator);
                Result result = new Result(fltsList);
                Root root = new Root();
                root.setResult(result);

                return root;
            }
            catch (ParseException e) {
                e.printStackTrace();
                System.out.println("parsing error");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("File reading error" + e.toString());
        }


        return null;
    }



}
