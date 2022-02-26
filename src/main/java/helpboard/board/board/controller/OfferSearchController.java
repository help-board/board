package helpboard.board.board.controller;

import helpboard.board.board.service.OfferManagementService;
import helpboard.board.board.view.OfferDto;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/offer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OfferSearchController {

    OfferManagementService offerManagementService;

    @GetMapping(value = "/{offerId}")
    public void details(@PathVariable(value = "offerId") UUID offerId) {
        offerManagementService.details(offerId);
    }

    @GetMapping(value = "/list")
    public void list() {
        offerManagementService.list();
    }
}
