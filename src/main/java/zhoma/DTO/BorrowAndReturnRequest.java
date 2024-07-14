package zhoma.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowAndReturnRequest {
  private Integer userID;
  private Integer bookID;
}
