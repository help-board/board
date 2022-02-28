package helpboard.board.board.repository;

import helpboard.board.board.model.Offer;
import helpboard.board.board.rest.view.OfferListDto;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferSearchRepository extends PagingAndSortingRepository<Offer, UUID> {

    @Query("SELECT new helpboard.board.board.rest.view.OfferListDto( " +
            "o.id, o.title, v.value, o.city, o.freeSpaceFrom, o.freeSpaceTo, o.active, o.created) " +
            "FROM Offer o LEFT JOIN Voivodeship v ON o.voivodeshipId = v.id WHERE " +
            "(:categoryId is null or o.categoryId = :categoryId) " +
            "and (:voivodeshipId is null or o.voivodeshipId = :voivodeshipId) " +
            "and (:persons is null or (o.freeSpaceFrom >= :persons and o.freeSpaceTo <= :persons))")
    Page<OfferListDto> search(@Param("categoryId") UUID categoryId,
                              @Param("voivodeshipId") Integer voivodeshipId,
                              @Param("persons") Integer persons,
                              Pageable pageable);

    @Query("SELECT new helpboard.board.board.rest.view.OfferListDto( " +
            "o.id, o.title, v.value, o.city, o.freeSpaceFrom, o.freeSpaceTo, o.active, o.created) " +
            "FROM Offer o LEFT JOIN Voivodeship v ON o.voivodeshipId = v.id WHERE " +
            "(:ownerId is null or o.ownerId = :ownerId)")
    Page<OfferListDto> findByOwnerId(@Param("ownerId") UUID ownerId, Pageable pageable);
}
