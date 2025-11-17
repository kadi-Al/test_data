from pydantic import BaseModel

class MyObject(BaseModel):
    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)
        self.__private_field = "secret"  # Example of a private field

    def to_dict(self):
        return {**self.dict(), "_MyObject__private_field": self.__private_field}

# Usage
obj = MyObject()
import json
print(json.dumps(obj.to_dict(), indent=4))
script
class MyObject {
    constructor() {
        this.__privateField = "secret"; // Example of a private field
    }

    toJSON() {
        const result = {...this};
        Object.defineProperty(result, '__privateField', { value: this.__privateField, enumerable: false });
        return result;
    }
}

const obj = new MyObject();
console.log(JSON.stringify(obj, null, 2));
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.lang.reflect.Field;

public class MyObject {
    private String __privateField = "secret"; // Example of a private field

    public String toDebugJSON() throws Exception {
        ObjectMapper mapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);
        Field[] fields = MyObject.class.getDeclaredFields();
        for (Field field : fields) {
            if (field.getName().startsWith("__")) {
                field.setAccessible(true);
                mapper.writeValueAsString(field.get(this));
            }
        }
        return mapper.writeValueAsString(this);
    }
}
sharp
using System;
using System.Reflection;
using Newtonsoft.Json;

public class MyObject
{
    private string __privateField = "secret"; // Example of a private field

    public string ToDebugJSON()
    {
        var jsonSerializerSettings = new JsonSerializerSettings();
        jsonSerializerSettings.Formatting = Formatting.Indented;
        jsonSerializerSettings.ReferenceLoopHandling = ReferenceLoopHandling.Ignore;
        jsonSerializerSettings.ContractResolver = new PrivateFieldResolver();
        return JsonConvert.SerializeObject(this, jsonSerializerSettings);
    }
}

public class PrivateFieldResolver : DefaultContractResolver
{
    protected override JsonProperty CreateProperty(MemberInfo member, MemberSerialization memberSerialization)
    {
        var property = base.CreateProperty(member, memberSerialization);
        if (property.DeclaringType == typeof(MyObject) && property.PropertyName.StartsWith("__"))
        {
            property.Readable = true;
            property.Writable = true;
        }
        return property;
    }
}