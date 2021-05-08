package ru.gbf.resourceserver.meta;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import ru.gbf.resourceserver.dto.CreateOrderEmailDto;
import ru.gbf.resourceserver.dto.GoodDTO;

import java.io.IOException;
import java.util.Map;

public class CreateOrderEmailDtoSerializer extends StdSerializer<CreateOrderEmailDto> {

    public CreateOrderEmailDtoSerializer() {
        this(null);
    }

    public CreateOrderEmailDtoSerializer(Class<CreateOrderEmailDto> t) {
        super(t);
    }

    @Override
    public void serialize(CreateOrderEmailDto createOrderEmailDto,
                          JsonGenerator gen, SerializerProvider provider) throws IOException {

        gen.writeStartObject();

        gen.writeStringField("to", createOrderEmailDto.getTo());
        gen.writeStringField("number", createOrderEmailDto.getNumber());
        gen.writeStringField("delivery", createOrderEmailDto.getDelivery());
        gen.writeNumberField("idCart", createOrderEmailDto.getIdCart());

        Map<GoodDTO, Integer> value = createOrderEmailDto.getGoods();

        gen.writeArrayFieldStart("goods");
        for (Map.Entry<GoodDTO, Integer> e : value.entrySet()) {
            gen.writeStartObject();
            gen.writeObjectFieldStart("good");
            gen.writeNumberField("id", e.getKey().getId());
            gen.writeStringField("name", e.getKey().getName());
            gen.writeNumberField("price", e.getKey().getPrice());
            gen.writeNumberField("category", e.getKey().getCategory());
            gen.writeEndObject();
            gen.writeNumberField("count", e.getValue());
            gen.writeEndObject();
        }
        gen.writeEndArray();
        gen.writeEndObject();
    }
}
