package io.avec.outputtracker.output;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@RequiredArgsConstructor // will add all @NonNull
@ToString
@Entity
public class Output {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NonNull
    private String dateTime;
    @NonNull
    private String username;
    @NonNull
    private String endpoint; // where we send the payload
    @NonNull
    private String eventType;
    @NonNull
    private String payloadType;
    @NonNull
    private String payload;

}
