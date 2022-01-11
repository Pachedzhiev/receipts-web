package com.example.demo.services;

import com.example.demo.data.models.Receipt;
import com.example.demo.data.models.User;
import com.example.demo.data.repositories.ReceiptRepository;
import com.example.demo.data.repositories.UserRepository;
import com.example.demo.services.models.ReceiptServiceModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReceiptService {
    private final ReceiptRepository receiptRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Autowired
    public ReceiptService(ReceiptRepository receiptRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.receiptRepository = receiptRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    public List<ReceiptServiceModel> findAll() {
        return this.receiptRepository.findAll().stream().map(e->modelMapper.map(e, ReceiptServiceModel.class)).collect(Collectors.toList());
    }



    public void save(ReceiptServiceModel receiptServiceModel, String user){
        Receipt ed = this.modelMapper.map(receiptServiceModel, Receipt.class);
        User usr = userRepository.findUserByUsername(user);
        usr.getReceipts().add(ed);
        ed.setUser(usr);
        this.receiptRepository.save(ed);
        this.userRepository.save(usr);
    }

    public List<ReceiptServiceModel> getByUsername(String username){
        return this.receiptRepository.getAllByUsername(username).stream().map(e-> modelMapper.map(e,ReceiptServiceModel.class)).collect(Collectors.toList());
    }

    @Transactional
    public void deleteReceipt(String id) throws Exception {
        Receipt receipt= this.receiptRepository.findById(id).orElseThrow(() -> new Exception("Receipt not found"));;
//        User user = this.userRepository.getByReceiptId(id);
//        user.getReceipts().remove(receipt);

        this.receiptRepository.delete(receipt);

    }


}
