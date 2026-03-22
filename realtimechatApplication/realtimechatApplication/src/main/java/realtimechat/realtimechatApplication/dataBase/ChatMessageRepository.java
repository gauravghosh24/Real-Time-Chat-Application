package realtimechat.realtimechatApplication.dataBase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessageEntity, Long> {
    // You can add custom query methods here later, e.g., List<ChatMessageEntity> findBySender(String sender);
}
