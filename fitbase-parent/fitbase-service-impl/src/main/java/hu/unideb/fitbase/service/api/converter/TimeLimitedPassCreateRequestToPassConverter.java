package hu.unideb.fitbase.service.api.converter;

import hu.unideb.fitbase.commons.pojo.request.TimeLimitedPassCreateRequest;
import hu.unideb.fitbase.service.api.domain.Pass;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TimeLimitedPassCreateRequestToPassConverter implements Converter<TimeLimitedPassCreateRequest, Pass> {
    @Override
    public Pass convert(TimeLimitedPassCreateRequest timeLimitedPassCreateRequest) {
        if(timeLimitedPassCreateRequest == null){
            return null;
        }
        return Pass.builder()
                .name(timeLimitedPassCreateRequest.getName())
                .price(timeLimitedPassCreateRequest.getPrice())
                .timeDuration(timeLimitedPassCreateRequest.getTimeDuration())
                .available(timeLimitedPassCreateRequest.getAvailable())
                .build();
    }
}
