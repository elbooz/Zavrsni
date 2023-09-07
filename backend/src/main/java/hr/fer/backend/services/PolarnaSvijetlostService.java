package hr.fer.backend.services;

import hr.fer.backend.model.PolarnaSvijetlost;
import hr.fer.backend.model.PrimaryKey;
import hr.fer.backend.repository.PolarnaSvijetlostRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.lang.Math.abs;
import static java.nio.charset.StandardCharsets.UTF_8;

@Service
@AllArgsConstructor
public class PolarnaSvijetlostService {
    private final PolarnaSvijetlostRepository polarnaSvijetlostRepository;

    @Scheduled(initialDelay = 5*60*1000, fixedDelay = 3*60*60*1000)
    @PostConstruct
    public void downloadDataPolarna() throws IOException {
        URL url = null;
        try {
            url = new URL("https://services.swpc.noaa.gov/json/ovation_aurora_latest.json");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        StringBuilder builder = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream(), UTF_8))) {
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                builder.append(str);
            }
        }

        String jsonStr = builder.toString();

        Gson gson = new Gson();

        Map map = gson.fromJson(jsonStr, Map.class);
        System.out.println(map.get("Forecast Time"));
        String datum = (String) map.get("Forecast Time");

        Timestamp vrijeme = new Timestamp(
                Integer.parseInt(datum.substring(0, 4))-1900,
                Integer.parseInt(datum.substring(5, 7))-1,
                Integer.parseInt(datum.substring(8, 10)),
                Integer.parseInt(datum.substring(11, 13)),
                Integer.parseInt(datum.substring(14, 16)),
                0,
                0
        );

        List<String> list = new ArrayList<>();
        int k = 0;
        String s = "";
        int otvoreno = 0;
        int tocka = 0;
        for (char c : map.get("coordinates").toString().toCharArray()) {

            if(c == '[') {
                if(k == 0) {
                    k = 1;
                    continue;
                } else {
                    otvoreno = 1;
                    s = "";
                }
            }
            if(c == '.') {
                tocka = 1;
                continue;
            }
            if(c != '0') {
                tocka = 0;
            }
            if(tocka == 1 && c == '0') {
                continue;
            }
            if (otvoreno == 1) {
                s += c;

            }
            if (c == ']') {
                otvoreno = 0;
                list.add(s);
            }

        }

        int x;
        int y;
        int value;
        String xx = "";
        String yy = "";
        String valuee;

        for(String str : list) {
            String[] zav = str.split(",");
            xx = zav[0].substring(1);
            yy = zav[1].substring(1);
            valuee = zav[2].substring(1, 2);

            x = Integer.parseInt(xx);
            y = Integer.parseInt(yy);
            value = Integer.parseInt(valuee);

            if(value != 0 && (y > 10 || y < -10) ) {
                polarnaSvijetlostRepository.save(new PolarnaSvijetlost(new PrimaryKey(vrijeme, x, y), value));
            }
        }


    }

    public List<PolarnaSvijetlost> getPolarnaSvijetlostByDatum(Timestamp datum) {
        return polarnaSvijetlostRepository.findAllByPrimaryKey_Datumvrijeme(datum);
    }

    public PolarnaSvijetlost getPolarnaSvijetlostByDatumAndCoordinates(Timestamp datumvrijeme, Integer longitude, Integer latitude) {
        return polarnaSvijetlostRepository.findByPrimaryKey(new PrimaryKey(datumvrijeme, longitude, latitude));
    }

    public Timestamp existByDatum(Timestamp datum) {
        return polarnaSvijetlostRepository.findNearestTimestamp(datum);
    }
}
