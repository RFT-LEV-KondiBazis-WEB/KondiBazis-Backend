package hu.unideb.fitbase.service.api.converter;

import hu.unideb.fitbase.commons.pojo.request.PassCreateRequest;
import hu.unideb.fitbase.service.api.domain.Pass;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PassCreateRequestToPassConverter implements Converter<PassCreateRequest, Pass> {
    @Override
    public Pass convert(PassCreateRequest passCreateRequest) {
        if (passCreateRequest == null) {
            return null;
        }
        return Pass.builder()
                .name(passCreateRequest.getName())
                .price(passCreateRequest.getPrice())
                .duration(passCreateRequest.getDuration())
                .passType(passCreateRequest.getPassType())
                .timeDuration(passCreateRequest.getTimeDuration())
                .passTimeDurationType(passCreateRequest.getPassTimeDurationType())
                .available(passCreateRequest.getAvailable())
                .build();
    }
}
