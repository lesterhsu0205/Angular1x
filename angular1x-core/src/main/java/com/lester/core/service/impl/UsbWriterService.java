package com.lester.core.service.impl;

import com.lester.core.service.IWriteService;
import org.springframework.stereotype.Service;

@Service
public class UsbWriterService implements IWriteService {

    @Override
    public void save() throws Exception {
        System.out.println("usb");
    }
}
