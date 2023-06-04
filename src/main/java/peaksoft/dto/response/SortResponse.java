package peaksoft.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Builder

public record SortResponse(
        Long id,
        List<String> list
) {
}
