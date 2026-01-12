package org.example.library;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * Главный класс Spring Boot приложения.
 * Точка входа для запуска всего приложения.
 *
 * <p>Этот класс инициализирует Spring Application Context,
 * запускает встроенный веб-сервер и настраивает автоматическую конфигурацию.</p>
 * SpringBootApplication - помечает класс как конфигурационный, настраивает приложение
 * на основе зависимостей, настраивает базу данных, шаблонизатор, регистрирует все компоненты
 */
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}