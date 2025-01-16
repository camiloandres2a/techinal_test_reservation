package com.techical_test_riservi.reservation.application.useCase.validators;

import com.techical_test_riservi.reservation.domain.Client;
import com.techical_test_riservi.reservation.domain.exceptions.CellPhoneNotValidException;
import com.techical_test_riservi.reservation.domain.ports.RetrieveClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientValidations {

    private RetrieveClients retrieveClients;

    @Autowired
    public ClientValidations(RetrieveClients retrieveClients) {
        this.retrieveClients = retrieveClients;
    }

    public void execute(String cellPhoneClient){
        if (!Client.isValidCellPhoneNumber(cellPhoneClient)) {
            throw new CellPhoneNotValidException("The cell phone number is invalid");
        }
    }

}
