package hu.unideb.fitbase.service.api.converter;

import hu.unideb.fitbase.commons.pojo.enumeration.PassType;
import hu.unideb.fitbase.commons.pojo.request.SuitablePassCreateRequest;
import hu.unideb.fitbase.service.api.domain.Pass;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SuitablePassCreateRequestToPassConverter implements Converter<SuitablePassCreateRequest, Pass> {
    @Override
    public Pass convert(SuitablePassCreateRequest suitablePassCreateRequest) {
        if (suitablePassCreateRequest == null) {
            return null;
        }
        return Pass.builder()
                .name(suitablePassCreateRequest.getName())
                .price(suitablePassCreateRequest.getPrice())
                .passType(PassType.SUITABLE)
                .duration(suitablePassCreateRequest.getDuration())
                .timeDuration(null)
                .available(suitablePassCreateRequest.getAvailable())
                .build();
    }
}
