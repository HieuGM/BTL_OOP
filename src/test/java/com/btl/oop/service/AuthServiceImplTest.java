//package com.btl.oop.service;
//
//import com.btl.oop.dto.RegisterForm;
//import com.btl.oop.entity.User;
//import com.btl.oop.repository.UserRepository;
//import com.btl.oop.service.AuthService;
//import com.btl.oop.service.impl.AuthServiceImpl;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.*;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.lang.reflect.Field;
//import java.time.LocalDate;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class AuthServiceImplTest {
//
//    @Mock
//    private UserRepository userRepository;
//
//    @Mock
//    private PasswordEncoder passwordEncoder;
//
//    @Captor
//    private ArgumentCaptor<User> userCaptor;
//
//    private AuthService authService;
//
//    @BeforeEach
//    void setUp() {
//        authService = new AuthServiceImpl(userRepository, passwordEncoder);
//    }
//
//    // --- Helpers ---
//    private static <T> T newRegisterForm(
//            String nickname, String username, String rawPassword,
//            String phone, String address, LocalDate birthday
//    ) {
//        try {
//            RegisterForm f = new RegisterForm();
//            setField(f, "nickname", nickname);
//            setField(f, "username", username);
//            setField(f, "password", rawPassword);
//            setField(f, "phoneNumber", phone);
//            setField(f, "userAddress", address);
//            setField(f, "birthday", birthday);
//            return (T) f;
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private static void setField(Object target, String fieldName, Object value) throws Exception {
//        Field field = target.getClass().getDeclaredField(fieldName);
//        field.setAccessible(true);
//        field.set(target, value);
//    }
//
//    @Test
//    void register_ShouldSaveUser_WhenNicknameNotExists() {
//        // given: valid form matching RegisterForm constraints
//        RegisterForm form = newRegisterForm(
//                "  johnny  ",      // sẽ trim
//                "john_doe",
//                "plainSecret",
//                "01234567890",     // ^0[0-9]{10}$ => 11 chữ số
//                "Hanoi",
//                LocalDate.of(2000, 1, 2)
//        );
//
//        when(userRepository.existsByNickname("johnny")).thenReturn(false);
//        when(passwordEncoder.encode("plainSecret")).thenReturn("ENCODED!");
//
//        // when
//        authService.register(form);
//
//        // then
//        verify(userRepository).existsByNickname("johnny");
//        verify(passwordEncoder).encode("plainSecret");
//        verify(userRepository).save(userCaptor.capture());
//
//        User saved = userCaptor.getValue();
//        assertEquals("johnny", saved.getNickname());
//        assertEquals("john_doe", saved.getUsername());
//        assertEquals("ENCODED!", saved.getPassword());
//        assertEquals("01234567890", saved.getPhoneNumber());
//        assertEquals("Hanoi", saved.getUserAddress());
//        assertEquals(LocalDate.of(2000, 1, 2), saved.getBirthday());
//        assertEquals(User.Role.USER, saved.getUserRole());
//        assertTrue(saved.getIsActive());
//    }
//
//    @Test
//    void register_ShouldThrow_WhenNicknameExists() {
//        RegisterForm form = newRegisterForm(
//                "dup", "any_user_01", "anySecret",
//                "01234567890", "Somewhere",
//                LocalDate.of(1999, 12, 31)
//        );
//
//        when(userRepository.existsByNickname("dup")).thenReturn(true);
//
//        IllegalArgumentException ex = assertThrows(
//                IllegalArgumentException.class,
//                () -> authService.register(form)
//        );
//        assertTrue(ex.getMessage().toLowerCase().contains("nickname"));
//
//        verify(userRepository, never()).save(any());
//        verify(passwordEncoder, never()).encode(any());
//    }
//
//    @Test
//    void register_ShouldTrimNicknameAndUsername() {
//        RegisterForm form = newRegisterForm(
//                "   nice   ",
//                "   user   ",
//                "pw",
//                "01234567890",
//                "   Hanoi   ",    // service chỉ trim nếu non-null
//                LocalDate.of(1998, 5, 10)
//        );
//
//        when(userRepository.existsByNickname("nice")).thenReturn(false);
//        when(passwordEncoder.encode("pw")).thenReturn("HASH");
//
//        authService.register(form);
//
//        verify(userRepository).save(userCaptor.capture());
//        User saved = userCaptor.getValue();
//
//        assertEquals("nice", saved.getNickname());
//        assertEquals("user", saved.getUsername());
//        assertEquals("HASH", saved.getPassword());
//        // Với code hiện tại: phone/address không null -> trim và set nguyên xi:
//        assertEquals("01234567890", saved.getPhoneNumber());
//        assertEquals("Hanoi", saved.getUserAddress()); // đã trim từ "   Hanoi   "
//    }
//}
