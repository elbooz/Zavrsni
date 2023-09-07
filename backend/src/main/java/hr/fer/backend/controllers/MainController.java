package hr.fer.backend.controllers;

import hr.fer.backend.model.*;
import hr.fer.backend.responseClasses.DatumResponse;
import hr.fer.backend.services.PolarnaSvijetlostService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
public class MainController {
    private final PolarnaSvijetlostService polarnaSvijetlostService;

    @CrossOrigin(origins = "*")
    @GetMapping("api/getByDatum")
    ResponseEntity<?> getDataByDatum(@RequestParam LocalDateTime DatumVrijeme) {
//        LocalDate date = DatumVrijeme.toLocalDate();
//        Date datum = Date.valueOf(DatumVrijeme.toLocalDate());
        Timestamp datumvrijeme = Timestamp.valueOf(DatumVrijeme);

//        List<BioluminiscentniPlanktoniResponse> planktioni = bioluminiscentniPlanktoniService.getBioluminiscentnePlanktoneByDatum(date);
//        List<Pozari> pozari = pozariService.downloadDataByDatum(datum);
//        List<Naoblake> naoblake = naoblakeService.downloadDataByDatum(datum);

        Timestamp closest = polarnaSvijetlostService.existByDatum(datumvrijeme);
        List<PolarnaSvijetlost> polarna = polarnaSvijetlostService.getPolarnaSvijetlostByDatum(closest);

        return ResponseEntity.ok(new DatumResponse(polarna, closest));
    }
}
////
////    @CrossOrigin(origins = "*")
////    @PostMapping("/api/getByDatumAndCoordinates")
////    void getCSVFile(HttpServletResponse servletResponse, @RequestBody String json) throws IOException {
////        RequestList requestList = new ObjectMapper().registerModule(new JSR310Module()).readValue(json, RequestList.class);
////
////        DatumResponse datumResponse = new DatumResponse(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
////
////        for(DatumAndCoordinatesRequest request: requestList.getRequestList()) {
////            LocalDate date = request.getDatumVrijeme().toLocalDate();
////            Date datum = Date.valueOf(date);
////            Timestamp datumvrijeme = Timestamp.valueOf(request.getDatumVrijeme());
////
////            Naoblake naoblaka = naoblakeService.downloadDataByDatumAndCoordinates(datum, request.getLongitude(), request.getLatitude());
////
////            if (naoblaka == null) {
////                naoblaka = new Naoblake(new PrimaryKeyId(datum, request.getLongitude(), request.getLatitude()), 2);
////            }
////            BioluminiscentniPlanktoniResponse plaktoni = bioluminiscentniPlanktoniService.getgetBioluminiscentnePlanktoneByDatumAndCoordinates(date, request.getLongitude(), request.getLatitude());
////
////            Pozari pozar = pozariService.downloadDataByDatumAndCoordinates(datum, request.getLongitude(), request.getLatitude());
////            if (pozar == null) {
////                pozar = new Pozari(new PrimaryKeyId(datum, request.getLongitude(), request.getLatitude()), 2);
////            }
////
////            Timestamp closest = polarnaSvijetlostService.existByDatum(datumvrijeme);
////            PolarnaSvijetlost polarna = polarnaSvijetlostService.getPolarnaSvijetlostByDatumAndCoordinates(closest, request.getLongitude(), request.getLatitude());
////            if (polarna == null) {
////                polarna = new PolarnaSvijetlost(new PrimaryKey(datumvrijeme, request.getLongitude(), request.getLatitude()), 0);
////            }
////
////            datumResponse.getNaoblake().add(naoblaka);
////            datumResponse.getPlanktoni().add(plaktoni);
////            datumResponse.getPozari().add(pozar);
////            datumResponse.getPolarna().add(polarna);
////        }
////
////        servletResponse.setContentType("text/csv");
////        servletResponse.addHeader("Content-Disposition","attachment; filename=\"PrirodnePojave.csv\"");
////        try (CSVPrinter csvPrinter = new CSVPrinter(servletResponse.getWriter(), CSVFormat.DEFAULT)) {
////            csvPrinter.printRecord("Vremenska Oznaka", "Duljina", "Sirina", "Naoblake", "Pozari", "Polarna svijetlost", "Bioluminiscentni planktoni");
////            for(int i = 0; i < datumResponse.getNaoblake().size(); i++) {
////                csvPrinter.printRecord(requestList.getRequestList().get(i).getDatumVrijeme(),
////                                        datumResponse.getNaoblake().get(i).getPrimaryKeyId().getLatitude(),
////                                        datumResponse.getNaoblake().get(i).getPrimaryKeyId().getLongitude(),
////                                        datumResponse.getNaoblake().get(i).getPrisutnost(),
////                                        datumResponse.getPozari().get(i).getPrisutnost(),
////                                        datumResponse.getPolarna().get(i).getPrisutnost(),
////                                        datumResponse.getPlanktoni().get(i).getPrisutnost());
////            }
////        } catch (IOException e) {
////
////        }
////
////    }
//}
