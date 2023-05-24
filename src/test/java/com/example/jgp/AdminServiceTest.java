package com.example.jgp;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.jgp.model.Admin;
import com.example.jgp.repository.AdminRepository;
import com.example.jgp.service.AdminService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AdminServiceTest {
    
    @InjectMocks
    AdminService adminService;

    @Mock
    AdminRepository adminRepository;

    @Test
    public void serviceCreates() {
        Admin admin = new Admin();
        admin.setNaziv("Jan Pol");
        admin.setMail("jpol@admin.com");
        when(adminRepository.save(any(Admin.class))).thenReturn(admin);

        Admin actual = adminService.create(new Admin());

        assertThat(actual.getNaziv().equals(admin.getNaziv())).isEqualTo(true);
    }
}
