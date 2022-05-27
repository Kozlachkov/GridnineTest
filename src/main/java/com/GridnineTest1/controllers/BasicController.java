package com.GridnineTest1.controllers;

import com.GridnineTest1.JsonSimpleParser;
import com.GridnineTest1.model.Flights;
import com.GridnineTest1.model.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class BasicController {

    private JsonSimpleParser jsonSimpleParser = new JsonSimpleParser();
    private Root root = jsonSimpleParser.parseMyProgram();
    private List<Flights> flights = root.getResult().getFlights();
    private List<Flights> oneChangeFlights = GetOneChangeFlights(flights);
    private List<Flights> zeroChangeFlights = GetZeroChangeFlights(flights);
    private List<Flights> oneAndZeroChangeFlights = Stream.concat(zeroChangeFlights.stream(), oneChangeFlights.stream()).collect(Collectors.toList());
    private List<Flights> currentListofFlights;
    private static byte tickNumber; //переключатель сортировки

    private List<Flights> GetOneChangeFlights(List<Flights> flights){
        List<Flights> flights1 = new ArrayList<>();
        for (Flights oneFlights : flights){
            if (oneFlights.getFlight().getLegs().get(0).getSegments().size()==2 &&
                oneFlights.getFlight().getLegs().get(1).getSegments().size()==2) flights1.add(oneFlights);
        }
        return flights1;
    }

    private List<Flights> GetZeroChangeFlights(List<Flights> flights){
        List<Flights> flights1 = new ArrayList<>();
        for (Flights oneFlights : flights){
            if (oneFlights.getFlight().getLegs().get(0).getSegments().size()==1 &&
                oneFlights.getFlight().getLegs().get(1).getSegments().size()==1) flights1.add(oneFlights);
        }
        return flights1;
    }

    @PostMapping()
    public String allActions (@RequestParam(value = "sort1", required = false) Boolean sort1,
                              @RequestParam(value = "sort2", required = false) Boolean sort2,
                              @RequestParam(value = "sort3", required = false) Boolean sort3,
                              @RequestParam(value = "change1", required = false) Boolean change1,
                              @RequestParam(value = "change2", required = false) Boolean change2,
                              @RequestParam(value = "lowerlimit", required = false) String lowerlimit,
                              @RequestParam(value = "upperlimit", required = false) String upperlimit,
                              ModelMap modelMap, Map<String, Object> message){
        //определяем где стоит значение сортировки
        Boolean[] sort = {sort1, sort2, sort3};
        byte count=0;
        for (int i=0; i<sort.length; i++){ //убираем значения null из пустых sort
            if (sort[i]==null) sort[i]=false;
            if (sort[i]) ++count;
        }
        //если произошло переключение сортировки, то обрабатываем их
        if (count>1){
            for (int i=0; i<sort.length; i++){ //убираем radio со старого места
                 if (tickNumber==i && sort[i]) {sort[i]=false; break;}
            }
            for (int i=0; i<sort.length; i++){//ставим метку tickNumber для radio на новое место
                if (sort[i]) {tickNumber=(byte)i; break;}
            }
        }

        // выбираем список полетов
        if (change1==null) change1=false;
        if (change2==null) change2=false;
        if (change1 && change2) currentListofFlights=oneAndZeroChangeFlights;
        else if(change1) currentListofFlights=oneChangeFlights;
        else if(change2) currentListofFlights=zeroChangeFlights;
        else currentListofFlights=flights;
        //определяем вид сортировки
        if (tickNumber==0) {Collections.sort(currentListofFlights, comparatorPrice1);}
        else if (tickNumber==1) {Collections.sort(currentListofFlights, comparatorPrice2); Collections.reverse(currentListofFlights);}
        else if (tickNumber==2) {Collections.sort(currentListofFlights, comparatorTime1);}

        //ограничения по цене
        int lowerprice, upperprice;
        String msg="";
        try {lowerprice = Integer.parseInt(lowerlimit);}
        catch (Exception e) {lowerprice=0; msg="ошибка ввода";}
        try {upperprice = Integer.parseInt(upperlimit);}
        catch (Exception e) {upperprice=50000; msg="ошибка ввода";}
        List<Flights> flightsListPriceLimits = new ArrayList<>();
        int readPrice;
        for (Flights fls : currentListofFlights){
            readPrice = Integer.parseInt(fls.getFlight().getPrice().getTotal().getAmount());
            if (readPrice>lowerprice && readPrice<upperprice) flightsListPriceLimits.add(fls);
        }
        currentListofFlights=flightsListPriceLimits;

        //передаем данные на вывод
        modelMap.addAttribute("flights", currentListofFlights);
        modelMap.addAttribute("sort1", sort[0]);
        modelMap.addAttribute("sort2", sort[1]);
        modelMap.addAttribute("sort3", sort[2]);
        modelMap.addAttribute("change1", change1);
        modelMap.addAttribute("change2", change2);
        modelMap.addAttribute("lowerlimit1", lowerprice);
        modelMap.addAttribute("upperlimit1", upperprice);
        message.put("message", msg);
        return ("/index");
    }

    @GetMapping()
        public  String index(ModelMap modelMap, Map<String, Object> message){
            currentListofFlights=flights;
            modelMap.addAttribute("flights", currentListofFlights);
            modelMap.addAttribute("sort1", true);
            modelMap.addAttribute("sort2", false);
            modelMap.addAttribute("sort3", false);
            modelMap.addAttribute("change1", false);
            modelMap.addAttribute("change2", false);
            modelMap.addAttribute("lowerlimit1", "0");
            modelMap.addAttribute("upperlimit1", "100000");
            message.put("message", "");
            tickNumber=0;
            return ("/index");
        }

        //компараторы
        Comparator<Flights> comparatorPrice1 = new Comparator<Flights>(){
            public int compare(Flights fl1, Flights fl2) {
                int price1 = Integer.parseInt(fl1.getFlight().getPrice().getTotal().getAmount());
                int price2 = Integer.parseInt(fl2.getFlight().getPrice().getTotal().getAmount());
                return (int) (price1 - price2);
            }
        };
        Comparator<Flights> comparatorPrice2 = new Comparator<Flights>(){
            public int compare(Flights fl1, Flights fl2) {
                int price1 = Integer.parseInt(fl1.getFlight().getPrice().getTotal().getAmount());
                int price2 = Integer.parseInt(fl2.getFlight().getPrice().getTotal().getAmount());
                return (int) (price1 - price2);
            }
        };
        Comparator<Flights> comparatorTime1 = new Comparator<Flights>(){
            public int compare(Flights fl1, Flights fl2) {
                int FlDuration1 = (int)(fl1.getFlight().getLegs().get(0).getDuration());
                int FlDuration2 = (int)(fl2.getFlight().getLegs().get(0).getDuration());
                return (int) (FlDuration1 - FlDuration2);
            }
        };

}
