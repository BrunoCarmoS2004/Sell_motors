import br.com.c137.project.sellmotors.vehiclecommand.multitenancy.tenant.models.AnuncioAttribute;
import br.com.c137.project.sellmotors.vehiclecommand.multitenancy.tenant.models.Location;
import br.com.c137.project.sellmotors.vehiclecommand.multitenancy.tenant.models.Picture;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ElementCollection
    @CollectionTable(name = "anuncio_channels", joinColumns = @JoinColumn(name = "anuncio_id"))
    @Column(name = "channel")
    private List<String> channels;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "anuncio_id")
    private List<Picture> pictures;

    @Column(name = "video_id")
    private String videoId;

    @Column(name = "category_id")
    private String categoryId;

    // Convertido para BigDecimal por boas práticas com moedas, mesmo vindo como String no JSON
    private BigDecimal price;

    @Column(name = "currency_id")
    private String currencyId;

    @Column(name = "listing_type_id")
    private String listingTypeId;

    @Column(name = "available_quantity")
    private Integer availableQuantity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location location;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "anuncio_id")
    private List<AnuncioAttribute> attributes;
}