package malllv.infra;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import malllv.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// @RequestMapping(value="/stores")
@Transactional
public class StoreController {

    @Autowired
    StoreRepository storeRepository;

    @RequestMapping(
        value = "stores/{id}/completecooking",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Store completeCookingStore(
        @PathVariable(value = "id") Long id,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /store/completeCookingStore  called #####");
        Optional<Store> optionalStore = storeRepository.findById(id);

        optionalStore.orElseThrow(() -> new Exception("No Entity Found"));
        Store store = optionalStore.get();
        store.completeCookingStore();

        storeRepository.save(store);
        return store;
    }

    @RequestMapping(
        value = "stores/{id}/acceptorder",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Store acceptStore(
        @PathVariable(value = "id") Long id,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /store/acceptStore  called #####");
        Optional<Store> optionalStore = storeRepository.findById(id);

        optionalStore.orElseThrow(() -> new Exception("No Entity Found"));
        Store store = optionalStore.get();
        store.acceptStore();

        storeRepository.save(store);
        return store;
    }

    @RequestMapping(
        value = "stores/{id}/rejectorder",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Store rejectStore(
        @PathVariable(value = "id") Long id,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /store/rejectStore  called #####");
        Optional<Store> optionalStore = storeRepository.findById(id);

        optionalStore.orElseThrow(() -> new Exception("No Entity Found"));
        Store store = optionalStore.get();
        store.rejectStore();

        storeRepository.save(store);
        return store;
    }

    @RequestMapping(
        value = "stores/{id}/startcooking",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Store startCookingStore(
        @PathVariable(value = "id") Long id,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /store/startCookingStore  called #####");
        Optional<Store> optionalStore = storeRepository.findById(id);

        optionalStore.orElseThrow(() -> new Exception("No Entity Found"));
        Store store = optionalStore.get();
        store.startCookingStore();

        storeRepository.save(store);
        return store;
    }
}
