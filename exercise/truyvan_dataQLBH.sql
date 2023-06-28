use quanlybanhang;
-- hiển thị thông tin gồm oID, oDate, oPrice của tất cả các hóa đơn trong bảng Order.
select o.oID, o.oDate, (od.odQTY * p.pPrice) as oPrice
from orders o join orderdetail od on o.oID = od.oID
 join product p on p.pID = od.pID;
-- hiển thị danh sách các khách hàng đã mua hàng và danh sách sản phẩm được mua bới các khách
select c.cName, p.pName, od.odQTY QTY
from customer c
join orders o on o.cID = c.cID
join orderdetail od on o.oID = od.oID
join product p on p.pID = od.pID;
-- hiển thị những khách hàng không mua bất kỳ sản phẩm nào
select c.cName
from customer c 
where not exists (select o.cID from orders o where o.cID = c.cID);
-- Hiển thị mã hóa đơn, ngày bán và giá tiền của từng hóa đơn (giá một hóa đơn được tính bằng
-- tổng giá bán của từng loại mặt hàng xuất hiện trong hóa đơn. Giá bán của từng loại được 
-- tính = odQTY*pPrice) 
select o.oID as MaHD, o.oDate as NgayBan, sum(od.odQTY * p.pPrice) as GiaTien
from orders o join orderdetail od on o.oID = od.oID
join product p on p.pID = od.pID
group by o.oID;