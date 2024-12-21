package dev.avinash.runnerz.run;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import java.time.LocalDateTime;

public record Run(
        @NotNull(message = "ID cannot be null")
        @Id
        Integer id,
        @NotBlank(message = "Title cannot be empty")
        String title,
        @NotNull(message = "StartedOn cannot be null")
        LocalDateTime startedOn,
        @NotNull(message = "CompletedOn cannot be null")
        LocalDateTime completedOn,
        @Positive(message = "Miles must be positive")
        Integer miles,
        @NotNull(message = "Location cannot be null")
        Location location,
        @Version
        Integer version

) {
    public Run {
        if (!completedOn.isAfter(startedOn)) {
            throw new IllegalArgumentException("CompletedOn must be after StartedOn");
        }
    }

}
